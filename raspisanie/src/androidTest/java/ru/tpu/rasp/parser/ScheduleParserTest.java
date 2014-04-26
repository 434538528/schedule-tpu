package ru.tpu.rasp.parser;

import junit.framework.TestCase;

import ru.tpu.rasp.TestUtils;
import ru.tpu.rasp.data.GroupLesson;
import ru.tpu.rasp.data.Lesson;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.data.WeekSchedule;

/**
 * @author andrey.pogrebnoy
 */
public class ScheduleParserTest extends TestCase {

	ScheduleParser parser;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		parser = new ScheduleParser();
	}

	public void testScheduleParser() throws Exception {
		Schedule parsedSchedule = parser.parse(TestUtils.wrapGrabberJSON(SCHEDULE_JSON));
		assertEquals(0, parsedSchedule.getType());
		Lesson lesson = parsedSchedule.getWeek(Schedule.ODD_AFTER_BREAKING).getDayLessons(WeekSchedule.MONDAY)[0];
		assertEquals("lesson title", lesson.subject);
		assertEquals("lesson time", lesson.startTime);
		assertEquals("lesson type", lesson.type);
		assertEquals("lesson sub1", lesson.getFirstSubtitle());
		assertEquals("lesson sub2", lesson.getSecondSubtitle());
	}

	private static final String SCHEDULE_JSON = "{\n" +
			"schedule: {\n" +
			"after: {\n" +
			"uneven: [\n" +
			"[\n" +
			"{\n" +
			"title: \"lesson title\",\n" +
			"time: \"lesson time\",\n" +
			"teacher: \"lesson sub1\",\n" +
			"type: \"lesson type\",\n" +
			"room: \"lesson sub2\"\n" +
			"}\n" +
			"],\n" +
			"[],[],[],[],[]" +
			"],\n" +
			"even: [\n" +
			"[],[],[],[],[],[]" +
			"]\n" +
			"},\n" +
			"before: {\n" +
			"uneven: [\n" +
			"[],[],[],[],[],[]" +
			"],\n" +
			"even: [\n" +
			"[],[],[],[],[],[]" +
			"]\n" +
			"},\n" +
			"type: 0\n" +
			"}\n" +
			"}";

}

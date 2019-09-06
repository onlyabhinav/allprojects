package org.abhinav.bugtracker.events
{
    import flash.events.Event;

    import org.abhinav.bugtracker.model.IssueVO;

    public class NewIssueEvent extends Event
    {

        public static var CREATE_ISSUE:String = "createIssue";

        public static var NEW_ISSUE:String = "newIssue";

        public var issue:IssueVO;

        public function NewIssueEvent(type:String, bubbles:Boolean = false,
                                      cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
        }

        public static function create(issue:IssueVO):NewIssueEvent
        {
            var event:NewIssueEvent = new NewIssueEvent(CREATE_ISSUE);
            event.issue = issue;
            return event;
        }
    }
}

package org.abhinav.bugtracker.events
{
    import flash.events.Event;

    import org.abhinav.bugtracker.constants.AppConstants;

    public class ChangeViewEvent extends Event
    {

        public static var UPDATE_VIEW:String = "UpdateViewEvent";

        public static var APP_HOME_VIEW:String = "appHomeView";

        public static var LOGIN_VIEW:String = "loginView";

        public static var SEARCH_VIEW:String = "portalView";

        public static var NEW_USER_VIEW:String = "newUserView";



        public var currentView:String; // = AppConstants.LOGIN_VIEW;

        public function ChangeViewEvent(type:String, bubbles:Boolean = false,
                                        cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
            this.currentView = currentView;
        }

        public static function changeTo(view:String):ChangeViewEvent
        {
            var event:ChangeViewEvent = new ChangeViewEvent(UPDATE_VIEW);
            event.currentView = view;
            return event;
        }
    }
}

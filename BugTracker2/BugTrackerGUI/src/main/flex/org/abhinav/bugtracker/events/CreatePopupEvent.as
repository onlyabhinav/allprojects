package org.abhinav.bugtracker.events
{
    import flash.events.Event;

    import org.abhinav.bugtracker.constants.AppConstants;

    public class CreatePopupEvent extends Event
    {

        public static var CREATE_POPUP:String = "createPopup";

        public static var POPUP_APP_MENU:String = "appMenuPopup";




        public var popup:String; // = AppConstants.LOGIN_VIEW;

        public function CreatePopupEvent(type:String, bubbles:Boolean = false,
                                         cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
            this.popup = popup;
        }

        public static function create(popup:String):CreatePopupEvent
        {
            var event:CreatePopupEvent = new CreatePopupEvent(CREATE_POPUP);
            event.popup = popup;
            return event;
        }
    }
}

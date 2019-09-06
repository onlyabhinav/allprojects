package org.abhinav.bugtracker.events
{
    import flash.events.Event;

    import org.abhinav.bugtracker.model.UserVO;

    public class NewUserEvent extends Event
    {

        public static var REGISTER_USER:String = "registerUser";

        public static var NEW_USER:String = "newUser";

        public var user:UserVO;

        public function NewUserEvent(type:String, bubbles:Boolean = false,
                                          cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
        }

        public static function create(user:UserVO):NewUserEvent
        {
            var event:NewUserEvent = new NewUserEvent(REGISTER_USER);
            event.user = user;
            return event;
        }
    }
}

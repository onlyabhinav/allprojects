package
{
    import flash.events.Event;

    public class AppEvent extends Event
    {
        public static var SERVICE_REQUEST:String = "ServiceRequest";

        public static var SERVICE_REQUEST2:String = "ServiceRequest";

        public function AppEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
        }
    }
}


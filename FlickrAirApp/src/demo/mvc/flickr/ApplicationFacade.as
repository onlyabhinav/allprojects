package demo.mvc.flickr
{
    import demo.mvc.flickr.controller.StartupCommand;

    import org.puremvc.as3.interfaces.IFacade;
    import org.puremvc.as3.patterns.facade.Facade;

    public class ApplicationFacade extends Facade implements IFacade
    {
        public static const STARTUP:String = "startup";

        public static const GET_IMAGES:String = "getImages";

        public static const IMAGE_LOADED:String = "imageLoaded";

        public static const IMAGE_SELECTED:String = "imageSelected";



        public static function getInstance():ApplicationFacade
        {
            if (instance == null)
                instance = new ApplicationFacade();
            return ApplicationFacade(instance);
        }

        public function startup(app:FlickrAirApp):void
        {
            sendNotification(STARTUP, app);
        }

        override protected function initializeController():void
        {
            super.initializeController();

            trace('initializeController__');

            registerCommand(STARTUP, StartupCommand);
            //registerCommand(GET_IMAGES, GetImagesCommand);
        }
    }
}

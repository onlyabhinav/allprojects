package demo.mvc.flickr.view
{
    import demo.mvc.flickr.ApplicationFacade;
    import demo.mvc.flickr.model.FlickrProxy;
    import demo.mvc.flickr.view.components.ImagePanel;

    import flash.events.Event;

    import mx.collections.ArrayCollection;
    import mx.controls.Alert;

    import org.puremvc.as3.interfaces.IMediator;
    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.mediator.Mediator;

    public class ImagePanelMediator extends Mediator implements IMediator
    {
        private var flickrProxy:FlickrProxy;

        public static const NAME:String = "ImagePanelMediator";

        public function ImagePanelMediator(viewComponent:ImagePanel = null)
        {
            super(NAME, viewComponent);
        }


        override public function onRegister():void
        {
            imagePanel.addEventListener(ImagePanel.SELECT, onSelect);
            imagePanel.addEventListener(ImagePanel.SEARCH, onSearch);

            flickrProxy = FlickrProxy(facade.retrieveProxy(FlickrProxy.NAME));

            trace('retrieveProxy(FlickrProxy.NAME)');
            imagePanel.images = flickrProxy.images;
        }

        private function get imagePanel():ImagePanel
        {
            return viewComponent as ImagePanel;
        }

        private function onSelect(event:Event):void
        {
            sendNotification(ApplicationFacade.IMAGE_SELECTED,
                             imagePanel.selectedImage);
        }

        private function onSearch(event:Event):void
        {
            flickrProxy.getImages(imagePanel.txtKey.text);
        }

        override public function listNotificationInterests():Array
        {
            return [ ApplicationFacade.IMAGE_LOADED ];
        }

        override public function handleNotification(notification:INotification):void
        {
            trace('handleNotification :: ' + notification.getName());
            switch (notification.getName())
            {
                case ApplicationFacade.IMAGE_LOADED:
                    imagePanel.images = notification.getBody() as ArrayCollection;
                    //                 Alert.show(imagePanel.images.toString());
                    break;
            }
        }
    }
}

package demo.mvc.flickr.view
{
    import demo.mvc.flickr.ApplicationFacade;
    import demo.mvc.flickr.model.vo.ImageVO;
    import demo.mvc.flickr.view.components.SingleImageView;

    import org.puremvc.as3.interfaces.IMediator;
    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.mediator.Mediator;

    public class SingleImageMediator extends Mediator implements IMediator
    {
        public static const NAME:String = "SingleImageMediator";

        public function SingleImageMediator(viewComponent:SingleImageView = null)
        {
            super(NAME, viewComponent);
        }

        private function get imageView():SingleImageView
        {
            return viewComponent as SingleImageView;
        }

        override public function listNotificationInterests():Array
        {
            return [ ApplicationFacade.IMAGE_SELECTED ];
        }

        override public function handleNotification(notification:INotification):void
        {
            trace('handleNotification :: ' + notification.getName());
            switch (notification.getName())
            {
                case ApplicationFacade.IMAGE_SELECTED:
                    imageView.currentImage = notification.getBody() as ImageVO;
                    break;
            }
        }
    }
}

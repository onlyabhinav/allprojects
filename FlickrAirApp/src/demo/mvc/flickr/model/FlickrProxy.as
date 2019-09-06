package demo.mvc.flickr.model
{

    import demo.mvc.flickr.ApplicationFacade;

    import mx.collections.ArrayCollection;
    import mx.controls.Alert;
    import mx.rpc.AsyncResponder;
    import mx.rpc.Responder;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;

    import org.puremvc.as3.patterns.proxy.Proxy;

    public class FlickrProxy extends Proxy
    {
        public static const NAME:String = "FlickrProxy";


        public function FlickrProxy(proxyName:String = null, data:Object = null)
        {
            super(NAME, data);
        }

        public function getImages(txtKey:String = 'Abhinav'):void
        {
            var delegate:FlickrServiceDelegate = new FlickrServiceDelegate();
            delegate.getImages(txtKey, new Responder(onResult,
                                                     onFault));
        }

        private function onFault(event:FaultEvent):void
        {
            Alert.show(event.fault.faultString, "Error");

        }

        private function onResult(event:ResultEvent):void
        {
            sendNotification(ApplicationFacade.IMAGE_LOADED,
                             event.result.rss.channel.item as ArrayCollection);
        }

        public function get images():ArrayCollection
        {
            return data as ArrayCollection;
        }
    }
}

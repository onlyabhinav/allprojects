package demo.mvc.flickr.model
{

    import mx.controls.Alert;
    import mx.rpc.AsyncResponder;
    import mx.rpc.AsyncToken;
    import mx.rpc.IResponder;
    import mx.rpc.Responder;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.http.HTTPService;

    public class FlickrServiceDelegate
    {

        private var responder:IResponder;

        private var service:HTTPService;


        public function FlickrServiceDelegate()
        {
            service = new HTTPService();
            service.url = "http://api.flickr.com/services/feeds/photos_public.gne";
        }

        public function getImages(key:String, responder1:Responder = null):void
        {
            var params:Object = new Object();
            params.format = 'rss_200_enc';
            //key = key.replace(/ /g, "_");
            params.tags = "'" + key + "'";

            var call:AsyncToken = service.send(params);
            call.addResponder(responder1);

            trace('FlickrServiceDelegate::getImages');
        }

        public function onLocResult(e:ResultEvent):void
        {
            Alert.show('Result..');
        }
    }
}

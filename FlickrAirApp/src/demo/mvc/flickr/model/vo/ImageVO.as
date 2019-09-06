package demo.mvc.flickr.model.vo
{

    [Bindable]
    public class ImageVO
    {
        public var name:String = "";

        public var path:String = "";

        public function ImageVO(name:String = null, path:String = null)
        {
            if (name != null)
                this.name = name;
            if (path != null)
                this.path = path;
        }

        public function get isValid():Boolean
        {
            return name != "" && path != "";
        }

    }
}

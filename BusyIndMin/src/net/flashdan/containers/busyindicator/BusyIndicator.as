package net.flashdan.containers.busyindicator
{

    import net.flashdan.containers.busyindicator.skins.BusyIndicatorSkin;

    import spark.components.SkinnableContainer;

    [SkinState("busy")]

    public class BusyIndicator extends SkinnableContainer
    {

        public function BusyIndicator()
        {
            super();
            setStyle("skinClass", BusyIndicatorSkin);
        }

        private var _busy:Boolean = false;

        public function set busy(val:Boolean):void
        {
            if (val != _busy)
            {
                _busy = val;
                invalidateSkinState();
            }
        }

        public function get busy():Boolean
        {
            return _busy;
        }

        override protected function getCurrentSkinState():String
        {
            var skinState:String = super.getCurrentSkinState();
            if (_busy)
            {
                skinState = "busy";
            }
            return skinState;
        }
    }
}

package org.abhinav.bugtracker.components.Reusable
{
    import flash.geom.ColorTransform;

    import mx.controls.ColorPicker;
    import mx.events.FlexEvent;
    import mx.utils.ColorUtil;

    import spark.components.BorderContainer;
    import spark.components.Label;
    import spark.effects.interpolation.RGBInterpolator;
    import spark.layouts.HorizontalAlign;
    import spark.layouts.HorizontalLayout;
    import spark.layouts.VerticalAlign;

    public class Captcha extends BorderContainer 
    {

        public var lenght:Number; // = 7;

        public var allowAlphaNum:Boolean = true;

        public var allowSpecialChar:Boolean = true;

        [Bindable]
        public var code:String = "";

        public function Captcha()
        {
            super();

            var layout:HorizontalLayout = new HorizontalLayout();
            layout.horizontalAlign = HorizontalAlign.CENTER;
            layout.verticalAlign = VerticalAlign.MIDDLE;
            layout.gap = 5;
            this.layout = layout;

            this.addEventListener(FlexEvent.CREATION_COMPLETE, refresh);
        }

        public function refresh(event:FlexEvent = null):void
        {
            if (this.numChildren != 0)
                this.removeAllElements();

            var t:Label;
            code = "";

            for (var i:int = 0; i < lenght; i++)
            {
                t = new Label();

                t.text = String.fromCharCode(getRandomCode());
                t.setStyle("fontWeight", (getRandomCode() % 2 == 0) ? "bold" : "normal");
                t.setStyle("fontSize", getRandomCode(14, 24));
                t.rotation = getRandomCode(-30, 30);

                var c:ColorTransform = new ColorTransform();
                c.redOffset = getRandomCode(0, 150);
                c.greenOffset = getRandomCode(0, 150);
                c.blueOffset = getRandomCode(0, 150);
                t.setStyle("color", c.color);

                this.addElement(t);

                code += t.text;
            }

            this.visible = true;
            this.toolTip = code;
        }

        protected function getRandomCode(min:Number = 48, max:Number = 90):Number
        {
            return Math.floor(Math.random() * (1 + max - min) + min);
        }
    }
}



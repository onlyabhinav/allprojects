package com.neorbs.skillmeter.values
{

    public class Skill
    {
        public var name:String = "";
        public var primaryCategory:String = "";
        public var secondaryCategory:String = "";
        public var experience:String = "";
        public var expUnit:String = "";
        public var level:Number = 0;
        public var certified:Boolean = false;

        public function validate():Boolean
        {
            if (name == "" || level == 0)
                return false;

            return true;
        }
    }
}


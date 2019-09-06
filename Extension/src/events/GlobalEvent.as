package events
{
	import flash.events.Event;
	
	public class GlobalEvent extends Event
	{
		public var data:Object;
		
		 
		public function GlobalEvent(data:Object,type:String='GlobalEvent', bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.data = data;
			
		}
		
		public static function openNewTab(eData:Object):GlobalEvent 
		{
			var event:GlobalEvent = new GlobalEvent(eData);
			
			return event;
			
			
		}
	}
}
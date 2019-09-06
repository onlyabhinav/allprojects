package datagrid
{
    import flash.events.Event;

    public class ItemUpdateEvent extends Event
    {
        public static var ITEM_UPDATED:String = "ItemUpdatedEvent";

        public static function dataUpdated(rowData:RowVO):ItemUpdateEvent
        {
            return new ItemUpdateEvent(rowData, ITEM_UPDATED);
        }

        public function ItemUpdateEvent(rowData:RowVO, type:String, bubbles:Boolean = true,
                                        cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
        }

        public var rowData:RowVO;
    }
}

package datagrid
{

    public class RowVO
    {

        private var _SSCI:MixItemVO;

        private var _address:MixItemVO;

        private var _crdsId:MixItemVO;

        private var _legalName:MixItemVO;

        private var _select:MixItemVO;

        public function get SSCI():MixItemVO
        {
            return _SSCI;
        }

        public function set SSCI(value:MixItemVO):void
        {
            _SSCI = value;
        }

        public function get address():MixItemVO
        {
            return _address;
        }

        public function set address(value:MixItemVO):void
        {
            _address = value;
        }

        public function get crdsId():MixItemVO
        {
            return _crdsId;
        }

        public function set crdsId(value:MixItemVO):void
        {
            _crdsId = value;
        }

        public function get legalName():MixItemVO
        {
            return _legalName;
        }

        public function set legalName(value:MixItemVO):void
        {
            _legalName = value;
        }

        public function get select():MixItemVO
        {
            return _select;
        }

        public function set select(value:MixItemVO):void
        {
            _select = value;
        }
    }
}

package
{
    import mx.controls.Alert;

    public class AppCommand
    {

        [Command(selector="ServiceRequest")]
        public function execute():void
        {

            Alert.show("Executed");
        }
    }
}


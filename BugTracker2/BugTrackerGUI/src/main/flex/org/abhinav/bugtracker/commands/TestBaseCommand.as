package org.abhinav.bugtracker.commands
{
    import avmplus.getQualifiedClassName;

    import mx.controls.Alert;
    import mx.rpc.AsyncToken;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.remoting.RemoteObject;

    import org.abhinav.bugtracker.events.BaseEvent;

    public class TestBaseCommand
    {

        [Inject]
        public var ro:RemoteObject;


        /*[Command(selector="getIssues")]*/

        /*[MessageHandler]*/
        [Command]//(selector="getIssues")]
        public function execute(e:BaseEvent):AsyncToken
        {
            trace('Invoked...');
            trace('Inside '+getQualifiedClassName(this));

            ro.addEventListener(ResultEvent.RESULT, resultHandler);
            return ro.ping();
            //this.event = event;
        }

        public function resultHandler(e:ResultEvent):void
        {
            Alert.show("Result: "+e.result.toString());
        }
    }
}



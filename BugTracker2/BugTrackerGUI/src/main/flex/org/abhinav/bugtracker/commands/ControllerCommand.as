package org.abhinav.bugtracker.commands
{
    import mx.controls.Alert;
    import mx.rpc.AsyncToken;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.remoting.RemoteObject;

    import org.abhinav.bugtracker.events.NewIssueEvent;
    import org.abhinav.bugtracker.model.ResultVO;

    public class ControllerCommand
    {

        [Inject]
        public var remoteObject:RemoteObject;

        [MessageDispatcher]
        public var dispatcher:Function;

        public function execute():AsyncToken
        {

            return remoteObject.getStatus();

        }

        public function ControllerCommand()
        {
            remoteObject.addEventListener(ResultEvent.RESULT, serviceResultHandler);
            remoteObject.addEventListener(FaultEvent.FAULT, serviceFaultHandler);
        }

        [MessgaeHandler]
        public function newIssueCommand(event:NewIssueEvent):void
        {
            trace('New Issue Requested: ' + token.message);

            var token:AsyncToken = remoteObject.newIssue(event.issue);

        }

        public function serviceResultHandler(event:ResultEvent):void
        {
            //if(event.)
            trace('Result Received' + event.messageId);
            var r:ResultVO = event.result as ResultVO;

            Alert.show(r.message);

            //dispatcher(NewIssueEvent.
        }

        public function serviceFaultHandler(event:FaultEvent):void
        {
            trace('Fault Received');
            //var r:ResultVO = event.result as ResultVO;
            Alert.show(event.token.toString());
        }



    }
}



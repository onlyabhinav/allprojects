package org.abhinav.bugtracker.model
{

    [RemoteClass(alias = "org.modules.issuetracker.controller.dto.IssueDTO")]
    public class IssueVO
    {
        public var AssignedTo:String = "";

        public var Build:String = "";

        public var Component:String = "";

        public var Description:String = "";

        public var Discoverability:String = "";

        public var EclipseConfig:String = "";

        public var IssueType:String = "";

        public var JDK:String = "";

        public var MileStone:String = "";

        public var OS:String = "";

        public var OSVersion:String = "";

        public var Project:String = "";

        public var Reproducibility:String = "";

        public var Severity:String = "";

        public var Version:String = "";

        public var RaisedBy:String = "";

        public var RaisedOn:String = "";

    }
}

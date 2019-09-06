package org.abhinav.bugtracker.constants
{
    import mx.collections.ArrayCollection;

    import org.abhinav.bugtracker.view.NewIssueView;

    [Bindable]
    public class NewIssueItems
    {

        public static var lstAssignedTo:ArrayCollection = new ArrayCollection([ 'Sandeep', 'Vijay', 'Sanjay', 'Vivek', 'Rahul' ]);

        public static var lstBuilds:ArrayCollection;

        public static var lstComponents:ArrayCollection;

        public static var lstDiscoverability:ArrayCollection;

        public static var lstEclipseConfig:ArrayCollection;

        public static var lstIssueTypes:ArrayCollection = new ArrayCollection([ 'Bug', 'New Request' ]);

        public static var lstJDK:ArrayCollection;

        public static var lstMileStone:ArrayCollection = new ArrayCollection([ 'Oct 2012 Release', 'Nov 2012 Release', 'Dec 2012 Release' ]);

        public static var lstOS:ArrayCollection;

        public static var lstOSVersion:ArrayCollection;

        public static var lstProjects:ArrayCollection = new ArrayCollection([ 'AIR', 'Flex 3', 'Flex 4', 'Flex 4.5', 'Flex 4.6' ]);

        public static var lstReproducibility:ArrayCollection;

        public static var lstSeverities:ArrayCollection;

        public static var lstVersions:ArrayCollection;


    }
}

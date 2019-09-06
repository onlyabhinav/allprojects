package org.abhinav.bugtracker.model
{

    public class LoginRequest
    {
        public var username:String;

        public var password:String;



        public function LoginRequest(username:String = '', password:String = '')
        {
            this.username = username;
            this.password = password;
        }
    }
}

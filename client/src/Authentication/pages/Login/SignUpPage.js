import React, {useState} from 'react';
import {useDispatch} from "react-redux";
import {signup} from "../../AuthenticationActions"
import {useHistory} from "react-router-dom";
import {NotificationContainer, NotificationManager} from "react-notifications";
import 'react-notifications/lib/notifications.css';

function SignUpPage() {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const dispatch = useDispatch();
    const history = useHistory();

    const handleSubmit = async (event) => {
        // Prevent page reload
        event.preventDefault();

        // TODO email check
        // TODO passwd check

        const response = await dispatch(signup({name: name, username: username, email: email, password: password}));

        if (response.success) {
            NotificationManager.success("You've been registered");
            history.push('/login');
        } else {
            NotificationManager.error(response.message);
        }

        console.log(response);
    };

    return(
        <div className="container">
            <h2>Sign Up</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="Name">Name</label>
                    <input type="text" className="form-control" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label htmlFor="Username">Username</label>
                    <input type="text" className="form-control" id="username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label htmlFor="Email">Email</label>
                    <input type="text" className="form-control" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label htmlFor="Password">Password</label>
                    <input type="password" className="form-control" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <button type="submit" className="btn btn-primary" disabled={!name || !username || !email || !password}>
                    Register
                </button>
            </form>
        </div>
    );
}

export default SignUpPage;
import React, {useState} from 'react';
import {useDispatch} from "react-redux";
import {useHistory} from "react-router-dom";
import {login} from "../../AuthenticationActions";
import {NotificationManager} from "react-notifications";

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const dispatch = useDispatch();
    const history = useHistory();

    const handleSubmit = async (event) => {
        // Prevent page reload
        event.preventDefault();

        const response = await dispatch(login({username: username, password: password}));

        if (response.success) {
            NotificationManager.success('Login successful');
            history.push('/');
        } else {
            NotificationManager.error(response.message);
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="Username">Username</label>
                    <input type="text" className="form-control" id="username" onChange={(e) => setUsername(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label htmlFor="Password">Password</label>
                    <input type="password" className="form-control" id="password" onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <div className="button-container">
                    <input type="submit" className="btn btn-primary" />
                </div>
            </form>
        </div>
    );
}

export default LoginPage;
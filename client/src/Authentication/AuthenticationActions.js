import callApi from '../util/apiCaller';

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGOUT = 'LOGOUT';

export function loginSuccess(data) {
    localStorage.setItem('user', JSON.stringify(data.user));
    localStorage.setItem('token', data.token);
    return {
        type: LOGIN_SUCCESS,
        payload: data,
        result: true,
    };
}

export function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    return {
        type: LOGOUT,
        payload: {},
        result: true
    };
}

export function login(credentials) {
    return async (dispatch) => {
        try {
            const response = await callApi('users/login', 'post', {username: credentials.username, password: credentials.password});

            if (response.token) {
                dispatch(loginSuccess({user: response.user, token: response.token}));

                return {success: true, message: 'Login Failed.'}
            } else {
                return {success: false, message: ''}
            }

            return response;
        } catch (e) {
            return {success: false, message: 'Could not process your request. Try again later.'}
        }
    };
}

export function signup(user) {
    return async (dispatch) => {
        try {
            const response = await callApi('users', 'post', {user: user});

            if (!response.success) {
                //dispatch()
            }

            return response;
        } catch (e) {
            return {success: false, message: 'Could not process your request. Try again later.'}
        }
    };
}

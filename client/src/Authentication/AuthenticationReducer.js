import {LOGIN_SUCCESS, LOGOUT} from './AuthenticationActions'

const initialState = {
    user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : null,
    token: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).token : null,
    error: null,
};

const AuthenticationReducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN_SUCCESS:
            return {
                ...state,
                loading: false,
                error: null,
                user: action.payload.user
            };
        case LOGOUT:
            return {
                ...state,
                loading: false,
                error: null,
                user: null
            }

        default:
            return state;
    }
}

export default AuthenticationReducer;
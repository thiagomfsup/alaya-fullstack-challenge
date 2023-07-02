import React from 'react';
import {AppBar, Button, IconButton, Link, Menu, MenuItem, Toolbar, Typography} from "@material-ui/core";
import {createTheme, ThemeProvider} from "@material-ui/core/styles";
import {AccountCircle} from '@material-ui/icons';
import {NotificationContainer} from "react-notifications";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../../Authentication/AuthenticationActions";

function Navbar() {
    const theme = createTheme({
        palette: {
            primary: {
                main: '#1ecde2'
            },
        },
    });

    const [anchorEl, setAnchorEl] = React.useState(null);

    const dispatch = useDispatch();

    const user = useSelector((state) => state.auth.user);
    const states = useSelector((state) => state);

    const handleLogout = () => {
        dispatch(logout());
    }

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <ThemeProvider theme={theme}>
            <AppBar position="fixed">
                <NotificationContainer />
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link href="/" className="text-white">Home</Link>
                    </Typography>
                    {!user && (
                        <div>
                            <Button href="/login" color="inherit">Login</Button>
                            <Button href="/signup" color="inherit">Sign Up</Button>
                        </div>
                    )}
                    {user && (
                        <div>
                            <IconButton
                                size="medium"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="true"
                                onClick={handleMenu}
                                color="inherit"
                            >
                                <AccountCircle/>
                            </IconButton>
                            <Menu
                                id="menu-appbar"
                                anchorEl={anchorEl}
                                anchorOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={Boolean(anchorEl)}
                                onClose={handleClose}
                            >
                                <MenuItem onClick={handleLogout}>Logout</MenuItem>
                            </Menu>
                        </div>
                    )}
                </Toolbar>
            </AppBar>
        </ThemeProvider>
    );

};

export default Navbar;

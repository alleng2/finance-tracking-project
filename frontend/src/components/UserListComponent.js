import React from 'react'
import UserListService from '../services/UserListService.js'

class UserListComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
    }

    componentDidMount() {
        UserListService.getUsers().then(response => {
            this.setState({users: response.data})
        });
    }

    render() {
        return (
            <table>
                <tbody>
                    <tr>
                        <th>Username</th>
                    </tr>
                    {this.state.users}
                </tbody>
            </table>
        )
    }

}

export default UserListComponent;
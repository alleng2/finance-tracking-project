import axios from 'axios'

class UserListService {

    getUsers() {
        return axios.get("http://localhost:8080/users");
    }

}

export default new UserListService();
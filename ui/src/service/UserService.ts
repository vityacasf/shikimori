import axios from "axios";
import {SaveUserResponse, User} from "../model/User";

class UserService {
    saveUser = (login: String, password: String, roleId: number): Promise<SaveUserResponse> => {
        return axios.post<SaveUserResponse>(
            `http://localhost:8080/api/v1/users`,
            {login: login, password: password, roleId: roleId},
            {withCredentials: true}
        ).then(response => response.data);
    }
}

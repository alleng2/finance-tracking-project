import logo from './logo.svg';
import './App.css';
import UserListComponent from "./components/UserListComponent.js";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
        >
          Users
        </a>
        <UserListComponent />
      </header>
    </div>
  );
}

export default App;

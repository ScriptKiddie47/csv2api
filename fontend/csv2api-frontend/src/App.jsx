import "./App.css";
import CSVTables from "./components/CSVTables";

function App() {
  return (
    <div className="p-4">
      <h1 className="text-2xl text-center bg-gray-300">CSV to API</h1>
      <CSVTables/>
    </div>
  );
}

export default App;

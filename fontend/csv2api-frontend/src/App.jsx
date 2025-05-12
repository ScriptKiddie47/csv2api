import "./App.css";
import CSVTableData from "./components/CSVTableData";
import CSVTableList from "./components/CSVTableList";

function App() {
  return (
    <div className="p-4">
      <h1 className="text-2xl text-center bg-gray-300">CSV to API</h1>
      <CSVTableList/>
      <CSVTableData/>
    </div>
  );
}

export default App;

import { use, useEffect, useState } from "react";
import { Link } from "react-router";

function CSVTableData() {
    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/getTableData/customers limit 3`)
            .then((res) => res.json())
            .then((res) => setTableData(res));
    }, []);
    return (
        <div className="py-2">
            <table className="table border border-2 border-double md:table-auto border-gray-400">
                <thead className="bg-green-300">
                    <tr>
                        <th className="border-r">Name</th>
                        <th className="border-r">Age</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        tableData.map((tdata) =>
                            <tr key={tdata['CUSTOMERID']} className="border-b border-black text-gray-700">
                                <td className="border-r">{tdata['CUSTOMERID']}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );
}



export default CSVTableData;

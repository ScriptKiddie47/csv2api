import { use, useEffect, useState } from "react";
import { Link } from "react-router";

function CSVTables() {
    const [tableNames, setTableNames] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/getCSVRecordList`)
            .then((res) => res.json())
            .then((res) => setTableNames(res));
    }, []);

    return (
        <div className="py-2">
            <nav className="bg-amber-200">
                <ul>
                    {tableNames.map((items) => (
                        <li key={items.csvFileName}>
                            <Link>
                                <p>{items.csvFileName}</p>
                            </Link>
                        </li>
                    ))}
                </ul>
            </nav>
        </div>
    );
}

export default CSVTables;

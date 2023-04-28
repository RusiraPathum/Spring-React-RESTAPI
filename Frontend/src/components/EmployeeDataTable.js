import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import editIcon from "./../assets/edit.png";
import deleteIcon from "./../assets/delete.JPG";
import "../App.css";

const EmployeeDataTable = () => {
  const navigate = useNavigate();
  const baseURL = "http://localhost:8080/api/v1/employee/getAllEmployee";
  const [employees, setEmployees] = useState([]);

  const setEmployeeData = () => {
    axios
      .get(baseURL)
      .then((response) => {
        setEmployees(response.data.content);
      })
      .catch((error) => {
        alert("Error Ocurred while loading data:" + error);
      });
  };

  useEffect(() => {
    setEmployeeData();
  }, []);

  const removeEmployee = (id) => {
    axios
      .delete(baseURL + "/employee/" + id)
      .then((response) => {
        alert("Employee record " + id + " deleted!");
        setEmployeeData();
        navigate("/read");
      })
      .catch((error) => {
        alert("Error Ocurred in removeEmployee:" + error);
      });
  };

  const removeAllEmployee = (id) => {
    axios
      .delete(baseURL + "/employees")
      .then((response) => {
        alert("All Employees deleted!");
        setEmployeeData();
        navigate("/read");
      })
      .catch((error) => {
        alert("Error Ocurred in removeEmployee:" + error);
      });
  };

  return (
    <div className="card-body">
      <br></br>
      <nav>
        <button
          className="btn btn-primary nav-item active"
          onClick={() => navigate("/create")}
        >
          Create New Employee
        </button>
      </nav>

      <br></br>
      <div className="col-md-6">
        <h4>Employees List</h4>

        <div className="container">
          <div className="row">
            <div className="col-12">
              <table className="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Mobile Number</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                  {employees &&
                    employees.map((employee, index) => (
                      <tr>
                        <th scope="row">{employee.empID}</th>
                        <td>{employee.empName}</td>
                        <td>{employee.empAddress}</td>
                        <td>{employee.empMobileNumber}</td>

                        <td>
                          <Link to={"/edit/" + employee.empID}>
                            <img
                              src={editIcon}
                              alt="Edit"
                              width="50"
                              height="30"
                              title="Edit"
                            />
                          </Link>

                          <button
                            onClick={() => removeEmployee(employee.empID)}
                            className="button"
                          >
                            {" "}
                            <img
                              src={deleteIcon}
                              alt="Remove"
                              title="Remove"
                              width="30"
                              height="30"
                            />
                          </button>
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <button
          className="btn btn-sm btn-danger"
          onClick={() => removeAllEmployee()}
        >
          Remove All
        </button>
      </div>
    </div>
  );
};
export default EmployeeDataTable;

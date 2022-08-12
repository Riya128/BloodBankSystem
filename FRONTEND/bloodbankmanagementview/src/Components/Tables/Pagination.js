import React from "react";

function Pagination({ usersPerPage, paginate, totalLength }) {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalLength / usersPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <div style={{ marginLeft: "5%" }}>
      <ul className="pagination">
        {pageNumbers.map((number) => {
          return (
            <li key={number} className="page-item">
              <a onClick={() => paginate(number)} className="page-link">
                {number}
              </a>
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default Pagination;

import React, {useEffect, useState} from "react";
import axios from "axios";

function Test(props) {
  // state data
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/test')
      .then((req) => {
        // 지역변수 data
        const {data} = req;
        console.log(data);
      })
      .catch((err) => {
        console.log('통신 시 오류가 발생했습니다.')
      });
  }, []);

  return (
    <div>
      <h3>스프링 프로젝트 안에서 동작하는 리액트</h3>
      <ul>
        {
          data.map((item, index) => {
            return <li key={index}>{item}</li>
          })
        }
      </ul>
    </div>
  );
}

export default Test;
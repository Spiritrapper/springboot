import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  
  const [num, setNum] = useState(0);

  const [numList, setNumList] = useState([]);

  function numRecoding () {
    setNumList([...numList, num])
  }

  return (
    <div className="App">
      <div>현재 숫자 : {num}</div>
       <button onClick={() => {setNum(num + 1)}}>숫자 증가</button>
       <button onClick={() => {setNum(num - 1)}}>숫자 감소</button>
       <button onClick={(numRecoding)}>숫자 기록</button>
       <h1>지정된 숫자</h1>
       <ul>
        {/*numList에 리스트로부터 map을 사용해 num해당되는 숫자를 꺼낸다. setNumList를 통해 새로운num이 리스트에 추가된다.*/}
        {numList.map((num)=>(
            <li>{num}</li>
        ))}
       </ul>
    </div>
  );
}

export default App;

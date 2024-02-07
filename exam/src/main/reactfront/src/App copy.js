import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

// 부모함수에서 매개변수 넘겼을때 받아야한다.
const RecordForm = ({numList, setNumList}) => {

  const [num, setNum] = useState(0);

  return (
    <div>
      <div>현재 숫자 : {num}</div>
      <button onClick={()=> setNum(num + 1)}>숫자 증가</button>
      <button onClick={()=> setNum(num - 1)}>숫자 감소</button>
      <button onClick={()=> setNum(0)}>숫자 초기화</button>
      <hr/>
      <button onClick={() =>setNumList([...numList,num])}>숫자 기록</button>
      <button onClick={()=> setNumList([])}>숫자 기록초기화</button>

    </div>
  )
}

const RecordList = ({numList}) => {


  return (


  <div>
  <h2>기록된 숫자</h2>
  {numList.length > 0 ? (
    <ul>
      {numList.map((num) => (
        <li >
          {num}
        </li>
      ))}
    </ul>
  ) : (
    <div>기록 없음</div>
  )}
</div>

)
}
// App 부모함수
const App= () => {

  // 여기에 선언하는 이유? : RecordForm, RecoredList에서도 사용하기위해 부모함수에 선언
  const [numList, setNumList] = useState([]);

 
  return (
    <div style={{margin : "0 auto,",
                 width : "800px",
                 textAlign : "center",
                }}>
    {/* */}
    <RecordForm numList={numList} setNumList={setNumList}/>
    <RecordList numList={numList}/>
  </div>
  )

  
  
}

export default App;

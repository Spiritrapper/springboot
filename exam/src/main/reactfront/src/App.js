import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  
  let [num, setNum] = useState(1);

  //setNum(2); 직접쓰면 안됨 계속 랜더링되기때문에

  // setTimeout(() =>{setNum(num = num +1)},1000);

  return (
    <div className="App">
      <header className="App-header">
       <div className='number'>{ num }</div>
       <div style={{backgroundColor:"blue"}}>{ num }</div>
       {/*<button onClick={()=>{setNum (num= num+1)}}>버튼</button>*/ }

      </header>
    </div>
  );
}

export default App;

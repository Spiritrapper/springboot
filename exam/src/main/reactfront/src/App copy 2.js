import logo from './logo.svg';
import './App.css';
import { useState } from 'react';



const App= () => {

  const [text, setText] = useState("")

  return (
    <div>
      <input types="text"
        value={text}
        onChange={(e) =>{
          console.log(e.target.value);
          setText(e.target.value);
        }}
      />
    </div>
  );
  
}

export default App;

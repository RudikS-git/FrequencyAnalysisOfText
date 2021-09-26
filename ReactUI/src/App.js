import React, { useEffect, useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import FormControl from 'react-bootstrap/FormControl'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import Container from 'react-bootstrap/Container'
import 'react-toastify/dist/ReactToastify.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import doRequest from './doRequest';
import Hat from "./Hat";
import TableFrequency from "./TableFrequency";
import BarChart from 'react-bar-chart';
import './BarChart.css';
import FrequencyStatContainer from "./FrequencyStatContainer";
import TextNameModal from "./TextNameModal";
import {Link, useHistory} from "react-router-dom";
import {useParams} from "react-router";

const App = () => {
  const [state, setState] = useState({inputText: "", analysisResult: {} });
  const [isSave, setIsSave] = useState(false);
  const { inputText, analysisResult } = state;
  const { letters, twoLetters, threeLetters, words, amountSymbols, amountNumbers } = analysisResult;

  const statsObject = [
    { number: amountSymbols, content: 'SYMBOLS'},
    { number: amountNumbers, content: 'NUMBERS'},
    { number: letters?.length, content: 'LETTERS'},
    { number: words?.length, content: 'WORDS'},
    { number: twoLetters?.length, content: 'BIGRAMS'},
    { number: threeLetters?.length, content: 'THREEGRAMS'},
  ];

  const { id } = useParams();
  const isViewing = Boolean(id);

  useEffect(async () => {
    if(id) {
      await getHistoryById(id);
    }

  }, [])

  const getHistoryById = async (id) => {
    const { success, data } = await doRequest(axios.get(`/analysis-by-id?id=${id}`));

    if(success) {
      setState({...state, analysisResult: data, inputText: data.text})
    }
  }

  const analyzeHandle = async () => {
    const { success, data } = await doRequest(axios.post(`/analyze`, { inputText }));

    if(success) {
      setState({...state, analysisResult: data})
      toast.success("The analyzer has finished working successfully")
    }

  }

  const saveHandle = async (textName) => {
    const { success, data } = await doRequest(axios.post(`/`, { ...analysisResult, text: inputText, textName: textName}));

    if(success) {
      setState({...state, analysisResult: data})
      toast.success("The analyzer result has created successfully")
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <Container className="container" fluid="xxl">
          <Row>
            <Col sm={{ span: 12, offset: 0 }}>
              <Hat content={isViewing && 'Text name - ' + analysisResult.textName}/>
            </Col>
          </Row>

          <Row md="24">
            <Col xs={12}>
              <InputGroup>
                <InputGroup.Text className="dark-background">Input text</InputGroup.Text>
                <FormControl
                    value={inputText}
                    className="input-area"
                    onChange={(e) => setState({...state, inputText: e.target.value})}
                    as="textarea"
                    aria-label="Input text"
                    disabled={isViewing}
                />
              </InputGroup>
            </Col>
          </Row>

          <Row className="d-flex justify-content-end">
            {
              !isViewing &&
                <>
                  <Col sm={1}>
                    <Button className="btn-blue" onClick={analyzeHandle} color="light" variant="info" disabled={inputText == ''}>Analyze</Button>
                  </Col>
                  <Col sm={1}>
                    <Button className="btn-blue" onClick={() => setIsSave(true)} color="light" variant="info" disabled={inputText == ''}>Save</Button>
                    <TextNameModal toggle={setIsSave} isOpen={isSave} okHandle={(text) => { saveHandle(text); setIsSave(false)} } />
                  </Col>
                </>
            }

            <Col sm={1}>
              <Link to="/history">
                <Button onClick={() => console.log('test')} color="light" variant="secondary">History</Button>
              </Link>
            </Col>
          </Row>

          <Row>
            <Col>
              <FrequencyStatContainer
                  items={statsObject}
              />
            </Col>
          </Row>

          <Row>
            <Col xs={6} md={6}>
              <TableFrequency firstColumn="Letter" items={analysisResult?.letters}/>
            </Col>

            <Col xs={6} md={6}>
              <TableFrequency firstColumn="Bigram" items={analysisResult?.twoLetters}/>
            </Col>
          </Row>

          <Row>
            <Col xs={12}>
              {
                analysisResult?.letters &&
                <BarChart
                    width={1300}
                    height={500}
                    margin={{top: 20, right: 20, bottom: 30, left: 40}}
                    data={analysisResult?.letters?.map(it => {

                      return { text: it.content, value: it.frequency }
                    })
                    }

                />
              }
            </Col>

            <Col xs={12}>
              {
                analysisResult?.twoLetters &&
                <BarChart
                    width={1300}
                    height={500}
                    margin={{top: 20, right: 20, bottom: 30, left: 40}}
                    data={analysisResult?.twoLetters?.map((it, index) => {

                      return { text: index, value: it.frequency }
                    })
                    }

                />
              }
            </Col>
          </Row>

          <Row>
            <Col xs={6} md={6}>
              <TableFrequency firstColumn="Threegram" items={analysisResult?.threeLetters}/>
            </Col>

            <Col xs={6} md={6}>
              <TableFrequency firstColumn="Words" items={analysisResult?.words}/>
            </Col>
          </Row>

          <Row>
            <Col xs={12}>
              {
                analysisResult?.threeLetters &&
                <BarChart
                    width={1300}
                    height={500}
                    margin={{top: 20, right: 20, bottom: 30, left: 40}}
                    data={analysisResult?.threeLetters?.map((it, index) => {

                      return { text: index, value: it.frequency }
                    })
                    }

                />
              }
            </Col>

            <Col xs={12}>
              {
                analysisResult?.words &&
                <BarChart
                    width={1300}
                    height={500}
                    margin={{top: 20, right: 20, bottom: 30, left: 40}}
                    data={analysisResult?.words?.map((it, index) => {

                      return { text: index, value: it.frequency }
                    })
                    }

                />
              }
            </Col>
          </Row>

        </Container>

      </header>
      <ToastContainer
          theme="dark"
      />
    </div>
  );
}

export default App;

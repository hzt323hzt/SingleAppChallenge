import React, {Component,useState,useEffect} from 'react';
import {PaymentResult,ItemTotal,Item} from '../utils/model'
import {BackTop , Layout, Button ,Divider,Row, Col, InputNumber,Table} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import {loadItems,getResult} from '../net/index'

const { Header, Content, Footer } = Layout;

function PayPage() {
  const [items, setItems] = useState<Array<ItemTotal>>([])
  const [result,setResult] =  useState<PaymentResult>({})
  // const [content, setContent] = useState<Array<JSX.Element>>([])

  const SubmitForm = async () => {
        const resp = await getResult(result)
        const res:PaymentResult = resp.data 
        if(res.name==="" || res.name ===null) alert("No this user")
        else {
            res.submitted = true;
            setResult(res)   
        }
    };
    const columns: ColumnsType<ItemTotal> = [
        {
          title: 'Item ID',
          dataIndex: 'id',
        },
        {
          title: 'Total before tax',
          className: 'column-money',
          dataIndex: 'total',
          align: 'right',
        },
        {
          title: 'Tax',
          className: 'column-money',
          dataIndex: 'tax',
          align: 'right',
        },
      ];    
    const paymentOutput = (result.submitted?
        <div>
        <Row gutter={16} justify = 'center'><Col><h3>Summary</h3></Col></Row>
        <Table
        columns={columns}
        dataSource={result.items}
        bordered
        rowKey={record => record.id}
        title={() => {return <Row justify='space-between'><Col span={8}>Name</Col><Col span={8}>{result.name}</Col></Row>}}
        footer={() => {return <Row justify='space-between'><Col span={8}>Total</Col><Col><h4 style={{textAlign:'right'}}>{result.total}</h4></Col></Row>}}
        /></div>
        :<div></div>
        );

     const componentDidMount = async () => {
        try{
            const resp = await loadItems()
            const res:Array<Item> = resp.data
            setItems(res)
            setResult({items:res})
        } catch (error) {}
    }

    useEffect(() => {
        componentDidMount()
        return () => {
        };
    }, [])

    return (
            <Layout>
                <Header>
                      
                </Header>
                <Content style={{
                            padding: '24px 200px 24px 200px',
                            margin: 0,
                            minHeight: 280,
                        }}>
                <Row key = 'userid' gutter={16} justify = 'center'><Col><h3>User ID</h3></Col>
                <Col>
                    <InputNumber defaultValue='0' onChange={(obj)=>{setResult({...result,id:Number(obj)})}}/>
                </Col>
                </Row>
                {items.map( (it) => {return <Row key = {'item'+it.id} gutter={16} justify = 'center'>
                    <Col span={4}><h3>Item {it.id}</h3></Col>
                    <Col span={4}><h3>Quantity: </h3></Col>
                    <Col span={8}>
                        <InputNumber defaultValue='0' onChange={(obj)=>{
                            let newitems = result.items;                            
                            if(newitems!= undefined){
                                const indexi = newitems.findIndex( (e)=>{return e.id===it.id})
                                newitems[indexi] = {...newitems[indexi],quantity:Number(obj)};}
                            setResult({...result,items:newitems});
                            } }/>
                    </Col>
                    </Row> })}  
                <Row gutter={16} justify = 'center'>
                <Col>
                    <Button onClick={SubmitForm}>Submit</Button>
                </Col>
                </Row>
                <Divider/>

                    {paymentOutput}
                </Content>
            </Layout>
    );
}

export default PayPage;

import React, {Component} from 'react';

import {BackTop , Layout, Button ,Divider} from 'antd';


const { Header, Content, Footer } = Layout;

const paymentInput = ();
const paymentOutput = ();

function Homepage() {
    return (
        <div> 
            <Layout>
                <Header>
                    <paymentInput/>    
                </Header>
                <Content>
                    <paymentOutput/>
                </Content>
            </Layout>
        </div>
    );
}

export default Homepage;

import React from 'react';
import {
  MDBFooter,
  MDBContainer,
  MDBIcon,
  MDBInput,
  MDBCol,
  MDBRow,
  MDBRipple,MDBBtn
} from 'mdb-react-ui-kit';

export default function MerchantFooter() {
  return (
    <MDBFooter className='text-center text-white' style={{ width:"100vw",display:"flex",position:"relative",bottom:'0px',backgroundColor: '#0d6efd' }}>
      <MDBContainer className='p-4'>
        <section className=''>
          <MDBRow>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/113.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/111.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/112.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/114.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/115.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
            <MDBCol lg='2' md='12' className='mb-4 mb-md-0'>
              <MDBRipple
                rippleColor='light'
                className='bg-image hover-overlay shadow-1-strong rounded'
              >
                <img src='https://mdbcdn.b-cdn.net/img/new/fluid/city/116.webp' className='w-100' />
                <a href='#!'>
                  <div
                    className='mask'
                    style={{ backgroundColor: 'rgba(251, 251, 251, 0.2)' }}
                  ></div>
                </a>
              </MDBRipple>
            </MDBCol>
          </MDBRow>
        </section>
        <div className='text-center p-3' style={{ backgroundColor: '#0d6efd', padding:'0px' ,margin:'0px'}}>
        © 2020 Copyright;
      </div>
      </MDBContainer>

      
    </MDBFooter>
  );
}
import React from "react";
import '../styles/AboutUs.css';

const AboutUs = () => {
    return (
        <div>
            <div class="about-section">
                <h1>About Us</h1>
                <p>EzLoans is a Loan Management System designed specifically for corporations to handle in-house employee purchases as well as facilitate the easy management of the different loans provided to such employees for purchasing the items.
                    Here, administrators can create new employees, items as well as loan cards, where as employees can apply for purchasing new items, view items they have purchased and also view the loan cards that they have been assigned. </p>
            </div>

            <h2>Our Team</h2>
            <div class="row">
                <div class="column">
                    <div class="card">
                        <img src="/w3images/team1.jpg" alt="Jane" />
                        <div class="container">
                            <h2>Yash Sahijwani</h2>
                            {/* <p class="title">CEO & Founder</p> */}
                            {/* <p>Some text that describes me lorem ipsum ipsum lorem.</p> */}
                            <p>Yash.Sahijwani@wellsfargo.com</p>
                            <p><button class="button">Contact</button></p>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <img src="/w3images/team2.jpg" alt="Mike" />
                        <div class="container">
                            <h2>Akash Singh</h2>
                            {/* <p class="title">Art Director</p>
                            <p>Some text that describes me lorem ipsum ipsum lorem.</p> */}
                            <p>mike@example.com</p>
                            <p><button class="button">Contact</button></p>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <img src="/w3images/team3.jpg" alt="John" />
                        <div class="container">
                            <h2>Sanchit Gupta</h2>
                            {/* <p class="title">Designer</p>
                            <p>Some text that describes me lorem ipsum ipsum lorem.</p> */}
                            <p>john@example.com</p>
                            <p><button class="button">Contact</button></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <div class="card">
                        <img src="/w3images/team1.jpg" alt="Jane" />
                        <div class="container">
                            <h2>Shubham Bhagat</h2>
                            {/* <p class="title">CEO & Founder</p> */}
                            {/* <p>Some text that describes me lorem ipsum ipsum lorem.</p> */}
                            <p>@wellsfargo.com</p>
                            <p><button class="button">Contact</button></p>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <img src="/w3images/team2.jpg" alt="Mike" />
                        <div class="container">
                            <h2>Vaibhav Kumar</h2>
                            {/* <p class="title">Art Director</p>
                            <p>Some text that describes me lorem ipsum ipsum lorem.</p> */}
                            <p>mike@example.com</p>
                            <p><button class="button">Contact</button></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AboutUs
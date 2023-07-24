<template>
  <div>
    <nav>
      <div id="title">
        <img id="logo" src="./assets/logo.png" alt="">
        <p id="brand">냉파대파</p>
      </div>
      <div id="pageLink">
        <router-link to="/">Home</router-link> |
        <router-link to="/search/">Search</router-link> |
        <router-link to="/about">About Us</router-link>
      </div>
      <div id="user">
        <router-link to="/signup">Signup</router-link> | 
        <router-link to="/login">Login</router-link>
      </div>
    </nav>
    <router-view/>
    <footer>
      <a href="#">Facebook</a> |
      <a href="#">Twitter</a> |
      <a href="#">Instagram</a>
      <p>@2023.panda. All rights reserved</p>
    </footer>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}


nav {
  padding: 25px;
  padding-top: 1.5rem;
  display: flex;
  justify-content: flex-start;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

nav #pageLink {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 2rem;
}

#logIn {
  margin-right: .5rem;
}

nav #title {
  display: flex;
  justify-content: left;
  margin-left: 0;
}

nav #user {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: auto;
}

#logo {
  width: 25px;
  height: 25px;
  margin-top: auto;
  margin-bottom: auto;
  margin-right: .5rem;
}

#brand {
  margin-top: auto;
  margin-bottom: auto;
  font-size: 20px;
  font-weight: bold;
}

footer {
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: #FD7E14;
  color: white;
}

</style>


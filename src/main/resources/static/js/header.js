const header = document.querySelector(".header")
const nav = document.querySelector("nav")
const mainContainer = document.querySelector(".container")

header.addEventListener("mouseover", e => {
    nav.classList.remove("hide")
})
mainContainer.addEventListener("mouseover", e => {
    nav.classList.add("hide")
})
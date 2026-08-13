const {defineStore}=Pinia
/*
	state : 저장공간 => 출력에 필요한 데이터를 모아서 관리
			멤버변수 역할 / 전역변수
*/
const initialState=()=>({
	list:[],
	curpage:1,
	totalpage:0,
	startPage:0,
	endPage:0,
	count:0
})

const useRecipeStore=defineStore('recipe_list',{
	// 공유변수
	state:initialState,
	//computed => 고정된 데이터, 계산 완료..
	getters:{
		range:(state)=>{
			const arr =[]
			for(let i = state.startPage; i<= state.endPage; i++)
			{
				arr.push(i)
			}
			return arr
		}
	},
	actions:{
		async recipeListData(){
			const res = await api.get('/recipe/list_vue',{
				params:{
					page:this.curpage
				}
			})
			console.log(res.data)
			this.setPageData(res.data)
		},
		setPageData(data){
			this.list = data.list
			this.curpage = data.pages[0]
			this.totalpage = data.pages[1]
			this.startPage = data.pages[2]
			this.endPage = data.pages[3]
			this.count = data.count
		},
		move(page){
			this.curpage = page
			this.recipeListData()
		}
	}
})
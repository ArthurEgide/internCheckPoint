package estagiario;

public class App {
  
  static void welcome(){
    System.out.println("############ Muito bem vindo ao ponto do estagiário ############");
    System.out.println("# O programa ainda está em desenvolvimento");
    System.out.println("# É necessário seguir os padrões de interações informados\n");
    System.out.println("# Quando houver '[EXEMPLO]', o interior das chaves é o padrão");
    System.out.println("# [hh:mm], significa que precisa entrar com esse formato de horário");
    System.out.println("# Ex: 00:00,  14:15, 19:30, 22:00\n");
    System.out.println("# É necessário seguir os padrões de interações informados");
    System.out.println("# Caso algum não esteja claro, por favor me informe\n");
    System.out.println("# Espero que te ajuda a se organizar!!\n\n");
    System.out.println("### Qualquer sugestão entre em contato (= ###");

  }
  public static void main( String[] args )
  {
    welcome();
    Manager manager = new Manager("Path_Alternative_File");
    User user = new User(manager);

    if(user.begin()){
      manager.addWorkDay(user.workinDay);
      manager.writer();
    }else{
      manager.writer();
    }

    
    user.close();


  }
}

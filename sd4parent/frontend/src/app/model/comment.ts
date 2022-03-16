import { User } from "./user";
import { Post } from "./post"

export class Comment {

  id: string;
  user: User;
  post: Post;
  text: string;
  date: string;

}

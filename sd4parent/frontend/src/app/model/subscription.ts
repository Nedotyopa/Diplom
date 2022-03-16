import {User} from "./user";
import {Post} from "./post";

export class SubscriptionPost {

  id: string;
  user: User;
  post: Post;
  duration: number;
  cost: number;
  status: boolean;

}
